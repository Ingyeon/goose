import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        // token 인증 방식
        token: localStorage.getItem('token') || '',  
        authError: null, // 오류 발생 시
        loginUser: {},
        
    },
    getters: {
        isLoggedIn: state => !!state.token,    // 로그인 했는지 확인
        authError: state => state.authError,   // 인증 에러
        authHeader: state => ({ Authorization: state.token}),  // 인증 정보
        loginUser: state => state.loginUser,  // 현재 로그인한 유저 
        
    },
    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_LOGIN_USER: (state, user) => state.loginUser = user,
        SET_AUTH_ERROR: (state, error) => state.authError = error,
        LOGOUT: () => { 
            localStorage.removeItem('user')
            location.reload();
        }
    },
    actions: {
        login({commit, dispatch},credential){
            axios({
                url: rest.accounts.login(),
                method: 'post',
                data: credential
            })
            .then(res => {
                const token = res.data.accessToken
                dispatch('saveToken', token)
                dispatch('fetchLoginUser')
                
                router.push({name: 'Home'})
            })
            .catch(err => {
                console.log("catch")
                console.error(err.response.data)
                commit('SET_AUTH_ERROR', err.response.data)
            })
        },
        saveToken({  commit  }, token) {
            commit('SET_TOKEN', token)
            localStorage.setItem('token', token)
        },
        removeToken({  commit  }){
            commit('SET_TOKEN', '')
            localStorage.setItem('token','')
        },

        signup({ commit, dispatch}, credentials){
            console.log("엑시오스 하기 전")
            console.log(credentials)
            axios({
                url: rest.user.user(),
                method: 'post',
                data: credentials
            })
            .then(res => {
                console.log("then")
                console.log('signup')
                // const token = res.data.accessToken
                // dispatch('saveToken', token)
                // router.push({ name: 'Home '})
            })
            .catch(err => {
                // console.log(url)
                console.log('catch')
                console.log(err)
                // console.error(err.response.data)
                commit('SET_AUTH_ERROR', err.response.data)
            })
        },
        // 현재 접속중인 이용자
        fetchLoginUser({ commit, getters, dispatch }) {
            console.log('작동')
            if (getters.isLoggedIn) {
              axios({
                url: rest.user.user_myprofile(), 
                method: 'get',
                headers: getters.authHeader,
              })
                .then(res => {
                    commit('SET_LOGIN_USER', res.data)
                console.log(getters.loginUser)}
                )
                .catch(err => {
                  if (err.response.status === 401) {
                    dispatch('removeToken')
                    router.push({ name: 'login' })
                  }
                })
            }
        },

        // logout({  getters , dispatch}){
        //     axios({
        //         url: rest.user.,
        //         method: 'post',
        //         headers: getters.authHeader,
        //     })
        //     .then( () => {
        //         dispatch('removeToken')
        //         alert('로그아웃 완료')
        //         router.push({ name: 'Home'})
        //     })
        //     .catch(err => {
        //         console.error(err.response)
        //     })
        // }
        logout({commit, dispatch}) {
            dispatch('removeToken');
            router.push({name:'Home'})
        },
        
        user_delete({commit, getters, dispatch}) {
            const Swal = require('sweetalert2')
            Swal.fire(
                '정말 탈퇴하실건가요??'
            )
            .then((result) => {
                console.log('then1')
                if (result.isConfirmed) {
                    axios({
                        url : rest.user.user(),
                        method: 'delete',
                        headers: getters.authHeader,
                        // data: userId
                    })
                    .then(dispatch('removeToken'))
                        console.log('then2')
                        Swal.fire(
                            '그동안 Goose를 이용해주셔서 감사합니다'
                        )  
                    router.push({name:'Home'})
                }
            })
    }
    }
}

//data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBYIBxgJFhQYGBgZGhgVGhkcGh0dHh0fGiMaHCMhJCEkITAlIR4sJBkcJzg0Ky8/NTg1Hic7QDs3Py40NTEBDAwMDw8QHxISHj8rJCw/ND80Oj81MTg2Nz81OD8zPjQ/QD4+MTg9NDQ/MT00QDQ7PT00NTQxND00NkA3PzE0NP/AABEIAOEA4QMBIgACEQEDEQH/xAAbAAEAAwEBAQEAAAAAAAAAAAAABQYHBAMCAf/EAEEQAAEDAgQCBwUFBQcFAAAAAAEAAgMEEQUGEiExQQcTIlFhcYEUMlKRoRVCYnKxFiNDguEzU5KywcLRJSY08PH/xAAZAQEAAwEBAAAAAAAAAAAAAAAAAgQFAwH/xAAhEQEBAQACAgIDAQEAAAAAAAAAAQIDEQQxEiEiUXFBI//aAAwDAQACEQMRAD8A2ZERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQERecsrYWdY4gAcSTYIPReM8zaaEzPIa1oLnOJsABzKquI9IlFRymBj31Dx9yBhkPlcdm/qotzZ8+1bYpYZaahjs97X2D6h19mmx2YALnz8rB9OxeuzlOY6J3s1KDpNU5t3yW46Gng3x48NwuDMGU6LA8OdW1OIVfWWJDzP2nOsbaW23N+SvFUHw0pp6eNt2jRGw9lg5XcRvpHGw3PDmq3UUMWXYZMyVZdVTxtuXFtwy5DQyFnBjbm1zva5Jsg4svZ/FHgUPtUNWS1gEtQ6F2gHvJ4kcN7LRKeZtTAJ2kOa4BzSOBB3BWV4XU1HSQAJqiKnpCd6aJwMkgB4PcdwLgcB6cxqdNA2lp207GhrWgNaBwAGwCD3REQEREBERAREQEREBERAREQEREBERAREQFzVtZHQUxqZHtYxou5zjYAL6qqllJTOqXuDWMaXuJ4ANFyfkqDTyDGqJ2cK5pFPGHSU9O61g1vuyOF7Okdta+wuLd6D7xnpLbTsb7PRzzdYSyJzh1bXu/C09tw/lCUGTp8fcMQxWVz3HdtKxxbEwcgdJ7R/rxXrkigM8Ls4VekSStL4w42bBB90C+zbixv3W8V2npIw0VPUe087a9D9F/z6bevBBYsOwmDC4hDDCyMDk1oC7lz0tQysgFRG5r2OF2uaQQQeYIX1UTNpad07nBrWgucTwAG5KD7cQwajt3lQGIZvw+ilMUlVCHcC3UHH1Auq/S00ufpDXSufFQXIihaSx8/LW8jdrDyA3PhztdBlykw6Pq46aJo8GN389kFExGlwPM8x6qpjgqCezJG7q3avI2D/17iF8nGsSyI4e1D22j2AnZ77R3m/6E2/EtCq8Epq2Iwvp4nNPEFjf+FU8TwafK8RqaQGelsetonku7PMxE3IIH3Tt3dyC24PisON0Da6CQPY7gRxB7iOII7ipFYtDVtyXiUWYqVzn4ZV2D2b3iceO3It3t5FvctkieJYxI0gggEEbgg7ghB6oiICIiAiIgIiICIiAiIgIiICIiAiIgoXS7Xmlyw2kaLmeVkRbe2pt7lt+V7AXUNjE0mNUcWXnPjeyStbT6oxpb1cLGvka0fC1wc0HwUp0uYY3EcMpg9xZGKhjHuFrtEl2B2/IEgnwX5R4MzBc14fg7N2Q09S+54ueSwFx8e0fmg/ekOb2qspctNOlkhMsobt+7jtZvkT9AV5mnjZB1WiMM93SWtDe6265KqT7Q6RKqo4inijp2nxcNTvq4j0UbnyhmxDAxHCC4te1xaOJAuNvIkH0WZ5OvlzTHfUafjZ+PFddd1I5ZlOWM3NwxptTVYc5rOUcreIb3NIIP/wAU90hTGqjpsvNJBq5gx5F/7KOzn8O+7R5EqrZg108VJXX7cNRAXHwdZjvqVZc1u6vPGFzn3SamMHlqe2PT87H5K54+7rjnan5OJnd6XKCJtPC2FoAa0BoA5AbBeyIu7gKHhzFTVGMHB2ygzNBJaAbdm1wHW0ki4uAbhfeZJ5KbL9RURAmRsMjmWFzqDSRYczfksz6KDUY7iDMRkaGwUsToYrA2e95Gp9zu52xuTzd5oJHFMCayvrcsgfuamJ1ZA34JWHtBvcNRafU9ym+ibEXYjkmLVxiLoPRltPyaQPRfWJytm6RaeFp3jpah0ngHlgbfz0n5KG6D7/s9N8PXut8h/RBpiIiAiIgIiICIiAiIgIiICIiAiIgIiII/GcMZjGFvw6QXZI0tPeO4jxBsfRZ7Q1U9BnSlpatp1wwVEZqPuTRjS4PB5PAZ2hxv9brjWa6TBJBFNM1rzwYO07/CN1Q83Sft2YYWxSwxRvL3SvboLmkFulrT2rnxFlDW85ndqeOPWr1I/Mrgz0suKOBBqZ5KgA8dLnHT9OHhZTgXxFG2GJsLRpa1oY0Dk1osB8gvtYnJv5buv22+PPxxM/pA51uMsSuHFuhw82uaR+itmaMLfmHKkc8JtPH1dTCfxtF7eRBI9QqnnZ+nK84722+oWj5Zv+z1Pfj1Mf8AlC0fBv8Azv8AWd50/OfxyZSzE3MeFiYdmVvYmjOzmPGxBHEA2Nv6FWBVXH8otr64YvTyupqposJW7h4+F7eDm7D5KPdjuKYUernoG1AH8SneN/5Hbgq6pL0onGsUhy5hL62TSxjATYADU48Ggc3EqsvzXiFb+7gwmRruGqd7WNb4959F6YZk+Wvrm4tiUonkabshaLQxnwH3j4lByYJTyUuX6zNlSNM9RG+QN/u2Bp6tm/A2I+e+910dDlN7PkaN5+++ST66f9qdMGI+w5KkjB3lc2MDvBNz9ArJlWg+y8t09CeLIo2u/NYF31JQS6IiAiIgIiICIiAiIgIiICIiAiIg/FnudMdmqsablimeY7s6yomHvMZ8LTyce/itCWQ5oIwPOs9RNdrKlsfVyEHRdtwWE8jzXLm1rOLczuuvBnOtyavUSuHYZFhjbRRhpPvPIu93i553JXYd1DQz626mO1Dva669evePvFYN5Lb3r23s8Uk6z6Si+XyCMXJsozrXu+85clbWx0MZlle1oHed/lxK8mrb1J9vbmSd6v05M6T9ZgUtuBDWtHi5wH6rXcLh9nw2OH4WMb8gFkWEYZPm7EGVDmOioY3h93Czp3N3Fh8P08zw2ZnujyW14nFrjx+Xu/bG8zlzyb/H1Pp9oiK0qCIiDMOkr/q2bcOwDi10nXPHg3/loctPWZUX/V+maWbi2lhDB4OcAf8Ae8ei01AREQEREBERAREQEREBERAREQEREBc1bRR19OaaVjXsdsWuAIPoV0ogoFX0W0b5OsgfPTOvf93IS35Ov9CuKTo3qmGzMVkt+KME/PUtKRQuM69yVKcm56tjEDl6cZzGW6ivm0ui61j2AN18bt3Ox2d38Fc8IyLRYXKJ9Dp5Abh8ztZB47NsGi3kuDpLP2bmrDca+GUwuPg4j9AXLQHUdzcO28V7MZnqdPdb1r3e3OxpkeG/+2UoNl5QQCId5717KSAiIgL5cdI1HgN19KDznXfZ2VKmr5tieB5uGkfUhBTeh8HEKiux4/x53W8rl1vTVZacqZ0UUHsGSIbixk1Sn+Y7fQBXNAREQEREBERAREQEREBERAREQEREBERAREQUDploDWZMdMBvE9sg8BwJ+RVmypiX2xlynxDm+Npd+YCzh6OBC6MeoBimCzUBF+sjcz1I2+tlQeh+sc7K7qImzqeaRno/tf5tSDT0XGys2s4eoXqKpvf9Cg90XOapo5k+hXm+s7h80HYs/wCmSs6nJb4Qbl72MsPO/wDoFbXTufz+So2fiKzGMOwb+8qWyO/JGRf6XQX/AAik9gwqKj+CNjP8LQP9F2oiAiIgIiICIiAiIgIiICIiAiIgIiICIiAiIgLKejpvW5txMQ2dTmQuDh7uvUSAPm70Vj6Scw/Y2B+yx3NRUnqYWD3iXWBIHhqA8yFIZJwBuWcvx4ft1hGuQ83Pdu7zA4DwAQSJpnDl9V8GNw4tPyUqiCH4JdS9rpZBEhpPAH5KiYvK1/S7RQvcGNjic4a9g5zw8Brb7F1yPl3rUlBZnyxBmai9mmbuLljxs5h7wf8ATggnUWfZSx+fDMbOUa5+qVovBMf4zBwv+KwPHfY8eJ0FAREQEREBERAREQEREBERAREQEREBERAXjPM2nhdM4gNaC5xPIAXJXss/6X8XdRZcGHR3MlS8RNA425/PYeqCLyHTuzhmaXOM27GPMVMw8GgcD5gH5uKkMRzAGZqnDWiSoYY6Olj42c9rZJJHbXawamgnuj7yrXlXBxgOX4cMFrsYA4jm87uPq4lcmNiHCahuIMgYameRkDSA1rnk/E7iWta0uPOzbILBFfqwCQTYXI4X5r0XPDUtme6NrmlzCGvAPukgOse42IPquhAREQQmFY4K7G6nCy3S6ncy2/vNe0EO/wAWoeim1nuOT/YXSJTYmdo6lpo5DyD73YT62F+QJWhIKZ0kZdONYOKyLappj10Lhxu2ziB56QR4gLuyNmMZny+yu2Eg7ErRye3jtyB4jz8FZVk+B/8AaHSlNhHuwVY62Mcg43dYDgLHW3yA70GsIiICIiAiIgIiICIiAiIgIiICIiAiIgLLc0AYl0v0NA/dkTOuA/F23D6xtK1JZTnJ/wBldK9BibjZj2iE93Fzf1kHyQasq9mrBJMYjilhn6mWGTrI3loe25a5hu07HZxsrADdfqCGy5hAwXD+o1Oe5zjJJI73pHu957vE2AA5AAKZREBERBUs94H9vYHJRN2ksJYjws9u4F/xbt9Qvzo/zSMw4SI3m1TF2JmHZ2pu2q3jb53Vlq4tbNQ4hUTMuSm4nWjF6aZ1NVD+I24a78wG+rxHHmEGiKhdKuX34lhLcVguJ6V3WsI46RYkDysHeirtfmLHMqUhqKhtPURNsDJbffhu0t38wV7UefMWrqZs7MKa9r2hzXDVZwPPc8CgvmTsfbmXL8eJNsHEaXt+F7dnDyvuPAhTqzXo4wiuoMaqKyWCOngn7ZhaSQH97Rc2533tvtyWlICIiAiIgIiICIiAiIgIiICIiAiIgKhdLGXX43l4VMV+tpz1rQOJA4gc78x5K+ogpGRc0DMeCNqg60rAGTN/EPv2+F3HwNwrZHVg7OFv0WaZqylUZexg5nwwXvczU43Dr7khvNp4kcRxC7sudIlLi7hSy3pp+BZIewT3Nfy8nWPmg0QSNPML7uokNu3UNx3jcL84IJbUBzXwZ2j7wUFX4lBhrA+eeOIOvpL3ht7cbX48RwUR+2uHmpbTCra57nNa0Ma9wLnGwFwyw38UFudWAcAT9FyvfrdqsPRfJaRfw49w9VRsz5wNTJ9g4aevqZOwZGbsiB2J1cNXjwbug4s3ynOWY48nwO7DHdZUyN3Dbfd7iW8PzHwKsGQMafTSuyhU2bPSjQw8BJE2wY4fy29Lc7qs4DR4lkKN8Aw5lSHO1Omie4ud9NVh4tChs55glxarhxFmH1NPVQkaZbF1xx0kBguASbeZ70G+Iq5kjHpMxYE2tlgdC8OLHNcCAS23abffSb8+BBG9rmxoCIiAiIgIiICIiAiIgIiICIiAiIgIiICrGZMkUeY2l0sQEh/iN2f8+fqrOuOsxKGgbeWaOMd73tZ+pCDGM0ZPqskYZ9pU+ISdW1zW6O022o2B94tPK+wXI3MeN0M8cBcJDI7RHcRuDza9tiOXfZXHpJzNQ4plabDmVUb5Dpc1rSXXLXB1gWgjl3qmQdaKrDZ3ya2mVha0s0lps3Ym+/yXLe7NZk/12xiXNt9x6YviWJS11LJiFKGRsqGta4xgAl4ILeJBuGk+ilM9Qey4ZG6FjGye0xaLNaLuGrT6XVm6aIteT2zf3c8UnG3xs/3rPcbNF1tN1EgdIaiEuGt77N2vuTbiQufJnvkzU+LXXHqLW3IuKZidevrtEZNzFGbkg8RsA0eupX/LuWqbLlL7PBGG/E47ud5u4qaRWVYREQEREBERAREQEREBERAREQEREBERAREQEREEbjuFtxrC34e5zmh4tqabOHiFjNdk2bK0pMtI2rg3PXMaS9o73Nvf14bcVu6KOs/KdJZ1c3uMNoMTFfKKPD6frXkAmzAxrAebnclZaTAYcEqGYtilawyMOtkYcGsY424N9559FosFIync57I2tLzdxa0AuPeSBufNZ7mHo16zE3YxRvY2QkudHM0PY4nuvci648fj44/uTu/u+3bk8jfJ9W9T9QzRj7c4YBJhdPDI9jwAJ32jYCxzXAjV2nC7eQVazPRtjpaHDmtaZjPF2WNGp2kWc4WF7X71I1OOzYKOqrqR8bt2sewao3kcGtI90m2wVnyRlt8EpzBVAGplA0tI/sWEe4O53efTvUJOXfJLqdSJ6vFjj6ze7V4REVtUEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREHFin/AIo/PF/nYuxfqICIiAiIgIiICIiAiIgIiICIiD//2Q==