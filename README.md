# task
1. Code is not multi thread safe. 
2. Most of operations was encapsulated and hidden by implementaters of `Mission` and `Rocket` interface.
3. Overall idea was to avoid using Mediator Pattern, which can complicate the solution. 
4. Author tool assumption that after `Mission` is finished rockets are comming into ground.