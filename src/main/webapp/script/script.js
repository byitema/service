const dom = (function () {
    const makeOrderForm = {
        addPositionButton: {
            type: "button",
            value: "Добавить позицию в заказ",
            id: "addPositionButton",
            positions: {
                "Internet": 1,
                "Statistics": 2
            },
        },
        submitButton: {
            type: "submit",
            value: "Сформировать заказ",
            id: "submitButton",
        },
    };

    let rowId = 1;

    function buildForm(form, type) {
        let array;
        switch (type) {
            case "makeOrderForm":
                array = makeOrderForm;
                break;
        }

        for (const prop in array) {
            const formDiv = document.createElement("div");

            if (array[prop].label != null) {
                const label = document.createElement("label");
                label.innerHTML = array[prop].label;
                formDiv.appendChild(label);
            }

            const input = document.createElement("input");
            input.type = array[prop].type;
            input.value = array[prop].value;
            input.required = true;

            if (array[prop].type === "submit" || array[prop].type === "button") {
                formDiv.id = array[prop].id;

                if (array[prop].id === "addPositionButton") {
                    input.onclick = function () {
                        const row = document.createElement("div");
                        row.id = "row" + rowId++;

                        const select = document.createElement("select");
                        const options = array[prop].positions;
                        for (let str in options) {
                            const child = new Option(str);
                            select.appendChild(child);
                        }

                        row.appendChild(select);

                        const amount = document.createElement("input");
                        amount.type = "number";
                        amount.min = "1";
                        amount.step = "1";
                        amount.required = true;
                        amount.name = "position0";

                        select.onchange = function () {
                            amount.name = "position" + (select.options.selectedIndex);
                        };

                        row.appendChild(amount);

                        const deleteBtn = document.createElement("input");
                        deleteBtn.type = "button";
                        deleteBtn.value = "X";
                        deleteBtn.onclick = function () {
                            form.removeChild(document.getElementById(row.id));
                        };

                        row.appendChild(deleteBtn);

                        form.insertBefore(row, document.getElementById("submitButton"));
                    };
                }
                if (array[prop].type === "submit") {
                    form.onSubmit = onSubmit();
                }
            } else {
                input.class = array[prop].class;
                input.placeholder = array[prop].placeholder;
                input.name = array[prop].name;
                input.min = array[prop].minValue;
                input.step = array[prop].step;
                input.id = array[prop].id;
            }
            formDiv.appendChild(input);
            form.appendChild(formDiv);
        }
    }

    function initPage() {
        const makeOrderForm = document.getElementById("make-order-form");
        if (makeOrderForm != null) {
            buildForm(makeOrderForm, "makeOrderForm");
            console.log("makeOrderForm");
            formId = "make-order-form";
            return;
        }
    }

    function onSubmit() {
    }

    return {initPage};
})();

dom.initPage();
