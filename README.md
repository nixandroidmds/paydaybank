# Architecture Diagram 
<img src="architecture diagram.png" width="594" height="333"/>

# Flow diagram for the provided user stories
* <img src="flow diagram for the provided user stories.png" width="682" height="723"/>

# Demo it with a working application (Video)
- https://drive.google.com/file/d/1b734WuVFcPexC_9B8o2UhVrv3sZ4Y8_K/view?usp=sharing

## How long did you spend on the coding test?
- 12% of the time spent on coding.

## What would you add to your solution if you had more time? If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.
- Custom Progress Dialog;
- Expandable list of categories;
- Filtering by category;
- Detailed transaction info;
- Search functionality;
- Better UX for filter screen;
- Unit Tests;
- Dark theme;
- TextFuture.

## What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.
- fold() and scan() for Collections:
```kotlin
class TransactionDomainListToUIMapper {
    ...

    fun map(...) {
        ...
            val amount = categorizedList.fold(0.0) { amount, entity -> amount + entity.amount }
        ...
    }
    ...
}
```

## How would you track down a performance issue in production?
1. I use LeakCanary in all my projects to track and fix memory leaks;
2. Besides LeakCanary I also use Android Profiler to detect drops in app performance.

## Have you ever had to do this?
- Using the techs described above is a part of my coding routine and I try to keep in mind potential problems.
So when a problem appears I have plenty of options to detect and fix them.

## How would you debug issues related to API usage? Can you give us an example?
If we talk about debugging it looks similar as debugging synchronous code for me with putting breaks before request is executed and in the result callback.
But in all projects I try to follow some simple rules to find backend related issues faster (or even prevent some):
- Using all ids as strings;
- Nullability checks for all fields;
- Using curl or postman to get required info about API call.

## How would you improve the Node server’s API that you just used?
1. Updating field names (following the convention it should have no spaces and snake_cased)
2. Uniform standard for date time (and add time zones in the pattern);
3. Adding token authentication;
4. Migration to https;
5. Securing personal transactions;
6. Add icons for categories;
7. Pagination;
8. Search functionality;
9. Filter functionality;
10. Forgot Password;
11. Validation for fields upon registration;
12. More severe password rules;
13. Dual Authentication;
14. Passport/ID Card validation;
15. Etc...

## Please describe yourself using JSON.
- [JSON about myself](I.json)