$(document).ready(function() {
    // Flatpickr 인스턴스 생성 및 설정
    $("#datePicker").flatpickr({
        inline: true,
        static: true,
        // 사용자가 날짜를 선택할 때 트리거되는 함수
        onChange: function(selectedDates, dateStr, instance) {
            if (!selectedDates.length) {
                return;
            }
            // 선택한 날짜를 'selectedDate' 입력 필드에 설정
            $("#selectedDate").val(dateStr);

            const today = new Date();
            today.setHours(0, 0, 0, 0);
            if (selectedDates[0] < today) {
                alert("영화 상영일은 오늘 날짜부터 선택 가능합니다.");
                instance.clear(); // 유효하지 않은 날짜 선택 해제
            }
        }
    });
});