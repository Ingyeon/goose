<template>
<h1>스터디 참여하기</h1>
<!-- 검색창 -->
<div class="input-group mb-3">
<select class="form-select-line-height">
  <option selected>상태</option>
  <option value="1">모집중</option>
  <option value="2">진행중</option>
  <option value="3">모집완료</option>
</select>
<select class="form-select-line-height">
  <option selected>분류</option>
  <option value="1">토익</option>
  <option value="2">면접</option>
  <option value="3">자유</option>
</select>
  <input type="text" class="form-control">
    <button class="btn btn-outline-secondary" type="button">검색</button>
</div>

<!-- 게시판 -->
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">글번호</th>
      <th scope="col">상태</th>
      <th scope="col">분류</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">등록일</th>
      <th scope="col">제목</th>      
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td colspan="2">Larry the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</template>

<script>

export default {
}
</script>