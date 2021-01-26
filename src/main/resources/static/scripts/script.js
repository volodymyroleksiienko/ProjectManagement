// Progress bar
let progressBar = document.getElementsByClassName('progress-bar');

Array.prototype.forEach.call(progressBar, function (el) {
    // remove padding left
    if (el.getAttribute('aria-valuenow') === '0') {
        el.classList.remove("pl-3");
    }
})

function editProjectModal(btnObj) {
    let trObj = btnObj.closest(".row-id");
    let trId = $(trObj).attr('id');

    console.log("item id:" + $(trObj).attr('id'));

    let name = $(trObj).find('.project-name').text();
    let description = $(trObj).find('.project-description').text();
    let sprintStart = $(trObj).find('.project-start').text();
    let sprintEnd = $(trObj).find('.project-end').text();

    $('#projectIdInput').val(trId);
    $('#editProjectNameInput').val(name);
    $('#editProjectDescriptionInput').val(description);
    $('#editStartDateInput').val(sprintStart);
    $('#editEndDateInput').val(sprintEnd);
    $('#deleteItemLink').attr('href','/projects/delete?id='+trId);
}

let tempId = document.getElementsByClassName("subtask-list-item").length;
console.log("temp: " + tempId);

$("#createSubtaskItem" ).on( "click", function() {
    let subTaskItem = $('#subtaskDescription').val();
    console.log('item: ' + subTaskItem);

    tempId = tempId + 1;
    console.log("temp_n: " + tempId);

    if (subTaskItem !== '') {
        $("#subtasksList").append('<li id="' + tempId +
            '" class="d-flex subtask-list-item" style="margin-bottom: 1rem;"><i class="fa fa-check color-green-dark align-self-center"></i><p class="subtask-description mb-0 align-self-center">' +
            subTaskItem +
            '</p><button type="button" onclick="deleteSubtaskModal(this)" class="edit-subtask-a ml-auto pl-1 align-self-center" style="width: 10%; border: none; background: transparent"><i class="fas fa-trash-alt fa-lg color-highlight"></i></button></li>'
        );

        document.getElementById(tempId).getElementsByTagName('button')[0].setAttribute('data-menu',
            'edit-subtask-modal');

        $('#subtaskDescription').val('');
        $('#subtaskDescription').focusout();
        console.log('added');
    }
});

function deleteSubtaskModal(btnObj) {
    let trObj = $(btnObj).parent().remove();
}

