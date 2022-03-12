var howToOpened = false;
var howToOpenImg = document.getElementById('how-to-open-img');
var howToStartText = document.getElementById('how-to-start-text');
var howToStartBlocks = document.getElementById('how-to-start-blocks');
if (howToOpenImg){
    howToOpenImg.addEventListener("click", function () {
        howToOpened = !howToOpened;
        openClose();
    });
    howToStartText.addEventListener("click", function () {
        howToOpened = !howToOpened;
        openClose();
    });
}
function openClose() {
    if (howToOpened) {
        howToOpenImg.style.transform = 'rotate(180deg)';
        howToStartBlocks.style.visibility = 'visible';
        howToStartBlocks.style.marginTop = '20px';
        howToStartBlocks.style.height = '335px';
    }
    else {
        howToOpenImg.style.transform = '';
        howToStartBlocks.style.visibility = 'hidden';
        howToStartBlocks.style.marginTop = '0';
        howToStartBlocks.style.height = '0';
    }
}