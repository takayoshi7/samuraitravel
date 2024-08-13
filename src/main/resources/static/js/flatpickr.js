let maxDate = new Date();
maxDate = maxDate.setMonth(maxDate.getMonth() + 3);

flatpickr('#fromCheckinDateToCheckoutDate', {
    mode: "range", // カレンダーの範囲選択モードを有効にする
    locale: 'ja', // カレンダーの言語を日本語にする
    minDate: 'today', // カレンダーで選択できる最小の日付を当日にする
    maxDate: maxDate // カレンダーで選択できる最大の日付を3か月後にする
});