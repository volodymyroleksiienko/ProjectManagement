// project backlogs sprint
$(document).ready(function () {
    let activeTab = localStorage.getItem('activeTab');
    console.log("Local storage: " + activeTab);
    if (activeTab) {
        $('#infoTab').removeAttr('data-tab-active');
        $('#' + activeTab).attr('data-tab-active', '');;
    } else {
        $('#infoTab').attr('data-tab-active', '');
    }
});

// Get active tab id
function getActiveId(aObj) {
    $('#infoTab').removeAttr('data-tab-active');
    $(aObj).attr('data-tab-active', '');
    console.log('current active: ' + aObj.id);
}

// Write to local storage active tabs id
$('.tabs-medium > a').on('click', function (e) {
    localStorage.setItem('activeTab', $(e.target).attr('id'));
    console.log('write to storage: ' + $(e.target).attr('id'));
});


// Progress bar
let progressBar = document.getElementsByClassName('progress-bar');

Array.prototype.forEach.call(progressBar, function (el) {
    // remove padding left
    if (el.getAttribute('aria-valuenow') === '0') {
        el.classList.remove("pl-3");
    }

    // remove click if 100%
    else if (el.getAttribute('aria-valuenow') === '100') {
        let trObj = $(el).closest('.row-id');
        let button = $(trObj).find('.edit-button');
        $(button).addClass('opacity-30');
        $(button).removeAttr('data-menu');
        $(button).removeAttr('onclick');
    }
});

// Sprints numbers
let sprintNumber = document.getElementsByClassName('font-40 opacity-20 mb-n1 icon-80');
for (let i = 0; i < sprintNumber.length; i++) {
    if (i < 10) {
        $(sprintNumber[i]).text('0' + (i + 1));
    } else if (i >= 10) {
        $(sprintNumber[i]).text(i + 1);
    }
}

function editSprintModal(btnObj) {
    let trObj = btnObj.closest(".row-id");
    let trId = $(trObj).attr('id');

    console.log("item id:" + $(trObj).attr('id'));

    let name = $(trObj).find('.sprint-name').text();

    let sprintStart = $(trObj).find('.sprint-start').text();
    let sprintEnd = $(trObj).find('.sprint-end').text();

    $('#sprintIdInput').val(trId);
    $('#editSprintNameInput').val(name);
    $('#editStartDateInput').val(sprintStart);
    $('#editEndDateInput').val(sprintEnd);
}

function editBacklogModal(btnObj) {
    let trObj = btnObj.closest(".backlog-id");
    let trId = $(trObj).attr('id');
    let projectId = $('body').attr('id');

    console.log("item id:" + $(trObj).attr('id'));

    let description = $(trObj).find('.backlog-description').text().trim();
    let priority = $(trObj).find('.backlog-priority').text().match(/\d+/);

    $('#backlogIdInput').val(trId);
    $('#backlogDescription').val(description);
    $('#editPriorities').val(priority);
    $('#deleteItemLink').attr('href','/backlogs/delete/'+projectId+'?'+trId);

    console.log('descr: ' + description + ' ; prior: ' + priority);
}
// project backlogs sprint
// tasks

// tasks
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

