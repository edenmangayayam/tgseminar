///<reference path='../libs/DefinitelyTyped/angularjs/angular.d.ts' />

///<reference path='../Model.ts' />
///<reference path='../Service.ts' />

module Todo {
        export interface Scope extends ng.IScope{
            todos:Model.Todo[];
            values:Model.Todo[];
            add : () => void;
            newContent:string;

            remove:(index)=>void;
            modify:(index, newValue)=>void;

    }
        export class Controller{
            constructor(public $scope:Scope, public todoService:Service.TodoService){
                this.$scope.todos = [
                     new Model.Todo("Hello")
                ];

                this.$scope.add = () => this.add();

                //this.$scope.values = this.$scope.index

                this.$scope.remove = (index) => this.remove(index);
                this.$scope.modify = (index, newValue) => this.modify(index, newValue);

                this.todoService.getList()
                    .success((todos:Model.Todo[]) => {
                        this.$scope.todos = todos;

                });

            }

            add():void{
                var content = this.$scope.newContent;
                var todo = new Model.Todo(content);
                this.$scope.todos.push(todo);
             }

            remove(index):void {
                this.$scope.todos.splice(index, 1);
            }

            modify(index,modifiedValue):void {
                this.$scope.todos[index] = new Model.Todo(modifiedValue);
            }}

            modifu(index : number):void{
            //     var newContent = this.window.prompt("test", this.$scope)

            }
}