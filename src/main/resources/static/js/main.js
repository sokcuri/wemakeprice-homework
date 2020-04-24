var app = new Vue({
  el: '#homework',
  data: {
    url: '',
    outputUnitCount: '',
    type: 'full_text',
    outputData: {
      alphabetValue: '',
      numericValue: '',
      ascendingSortedAlphabetValue: '',
      ascendingSortedNumericValue: '',
      mixedValue: '',
      quota: '',
      remainder: ''
    }
  },
  methods: {
    isNumber: function(e) {
      let keyCode = e.keyCode;
      if (keyCode < 48 || keyCode > 57) {
        e.preventDefault();
      }
    },
    submit: function() {
      if (this.url === '') {
        alert('URL을 입력해주세요.');
        return false;
      }
      if (this.outputUnitCount === undefined || this.outputUnitCount < 1) {
        alert('1 이상의 자연수를 입력해주세요.');
        return false;
      }
      axios({
        url: 'http://localhost:8080/api/crawling',
        method: 'post',
        data: {
          url: this.url,
          type: this.type,
          outputUnitCount: this.outputUnitCount
        }
      }).then((response) => {
        console.log(response);
        this.outputData.alphabetValue = response.data.alphabetValue;
        this.outputData.numericValue = response.data.numericValue;
        this.outputData.ascendingSortedAlphabetValue = response.data.ascendingSortedAlphabetValue;
        this.outputData.ascendingSortedNumericValue = response.data.ascendingSortedNumericValue;
        this.outputData.mixedValue = response.data.mixedValue;
        this.outputData.quota = response.data.divisionTextValue.quota;
        this.outputData.remainder = response.data.divisionTextValue.remainder;
      }).catch((ex) => {
        alert(ex);
      })
    }
  }
});