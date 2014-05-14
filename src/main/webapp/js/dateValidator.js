function  isDateValid(str) {
        var dateParts = /^(\d\d(?:\d\d)?)-(\d\d?)-(\d\d?)$/.exec(str);
        if (dateParts === null)
        {
            return null;
        }
        var year = parseInt(dateParts[1]);
        if (year < 100)
        {
            year += 2000;
        }
        var month = parseInt(dateParts[2]) - 1;
        var day = parseInt(dateParts[3]);
        var result = new Date(year, month, day);
        return year === result.getFullYear()
            && month === result.getMonth()
            && day === result.getDate() ? result : null;
}