document.querySelector('#sprite_more_icon').addEventListener('click', function() {
    var toggleBox = this.querySelector('.toggle_box');
    toggleBox.style.display = toggleBox.style.display === 'none' ? 'block' : 'none';
});

document.querySelector('#follow').addEventListener('click', function(event) {
    this.value = this.value === '팔로우' ? '팔로우 취소' : '팔로우';
    event.stopPropagation();  /* 클릭 이벤트가 상위 요소로 전파되는 것을 막음 */
});

document.querySelector('#edit').addEventListener('click', function(event) {
    this.value = this.value === '수삭' ? '삭제' : '수정';
    event.stopPropagation();  /* 클릭 이벤트가 상위 요소로 전파되는 것을 막음 */
});