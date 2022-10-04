### Summary
For global technical challenge. 
- Version: 0.1 
- Data: 04/10/2022
- Use: Selenium 4.4.0 + Java 1.8

### Requirement (TestCase)
##### website: [Link](http://automationpractice.com/index.php)
##### Step:  
1. Add to the cart a Faded Short Sleeve T Shirt, size medium, colour blue 
2. Add to the cart an Evening Dress, size small, colour beige 
3. Add to the cart a Printed Summer Dress, size medium, colour orange 
4. Checkout 
5. Remove the Evening Dress from the cart 
6. Add a second Faded Short Sleeve T Shirt of the same size and colour 
##### Assert:
1. Assert the total for each line in the cart 
2. Assert the cart total is $65.53 

### Issue
##### Issue 1: Hard to locate production on homepage
  - On website home page, there is no use of "Id" in the product list, making it more difficult to locate and not easy to maintain afterwards. However, the id is complete on the subsequent pages and helps a lot with the progress of the work.
##### Issue 2: "Evening Dresses" is a catagory, and no "Evening Dress" in it. 
  - "Evening Dress" is a catagory, not a product. But it didn't explain on requirement, this will make the test case inaccurate. 
  - Considering that this product will be removed later, it seems that the test object is remove function, then I don't think this step really cares about which product, so I choose "add to the cart an product from Evening Dresses catagory". 
##### Issue 3: Product of the same name in the use case
  - Using products with the same name as test subjects here is not good in my opinion, it makes the test points for this step too complex and can easily lead to test failures without clarity on what is happening.
  - I think it would be better to separate out the duplicate name case. 
##### Issue 4: Case failed cause by discount 
  - Last assert failed by discount, but case doesn't update for that. 
  - I think it depends on the test objective of the test case whether to make it variable or not, hard code makes it easier to know if there are errors in the whole process, but it is hard to locate which step is wrong, but variables make it better to calculate the price in this use case, but at the same time checking the unit price becomes necessary.
  
### Thoughts & To-do
- Clear test points for each use case. 
  - As the requirements do not specify which pages are to be tested, and there are multiple entry points on the page that provide the same functionality, it is difficult to specify test coverage on UI
  - Maybe it would be better to distinguish between a UI testing section and an API testing section. 
  
- Optimise code with testNG
  - Although this project didn't use testNG, but considering that if more use cases are required and reports can be easily generated, TestNG should be a better choice. 
