What technical debt has been cleaned up
========================================

We cleaned up technical debt involving our project database. We originally were including a jar file for SQLite, and we’ve updated our project to instead include an HSQL dependency in our build.gradle file. This allowed us to use a script file to build the database and copy the database to the device on startup so it’s accessible for read/write. This was an example of deliberate and reckless technical debt.
https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/commit/480e030356b01f3f4696ebc03a309dc986da34aa


What technical debt did you leave?
==================================

One example of technical debt that we decided to leave involves how we store images for our application. When we first designed the app, images were stored as integers, and we continued developing with this in mind. Later, we wanted to include a feature where users could upload their own images from the device, but to do this we would have to change the image type to bitmap. We decided that it would take too much time to refactor the project/database to accommodate this change, so we didn’t end up doing it. This was an example of deliberate and prudent technical debt.

Discuss a Feature or User Story that was cut/re-prioritized
============================================

One feature we decided to cut from our project was the ability to temporarily hide clothing. This feature was created at the start of the project, as an idea of something nice to have for our application. This feature was planned for the 3rd iteration however, once we started planning at the start of the 3rd iteration, we thought about whether we should keep it. Our team decided that this feature was low priority, and it would take too much time away from other features in the iteration, so we decided to cut it.
https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/issues/15

Acceptance test/end-to-end
==========================

One of the test we have written is adding new outfit

1. Type in w/ username "user", password "password"

2. Click Login

3. Using navigation bar at the bottom, click "Outfits"(Find the navigation bar on the bottom middle, and check that the middle icon is selected If not, click that icon)

4. Click the ADD button.

5. Type in the text bar with "NewOutfit"

6. Click the button with the "PLUS" icon.

7. Check the first item of outfits list with name "NewOutfit"

8. Navigate to account tab.(Find the navigation bar on the bottom middle, and check that  click the leftmost icon)

9. Click Sign out.

In order to avoid flaky tests, any data required for the testing needs to be set up before the testing begins. That is, we delete or add items to a closet depending on the aspect of the changes that occur during the test. In adding the new outfit test mentioned above. By using @Before in Junit test, we check if the outfit with name "New Outfit" exists, if it exists we delete the outfit from the closet.

link to test: [AddOutfitTest file](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/blob/main/app/src/androidTest/java/com/example/virtualcloset/addOutfitTest.java)


Acceptance test, untestable
===============

We have written tests for login in or sign up an account, adding or removing clothes , adding or removing flags of clothes , adding or removing outfits , sorting clothes, adding clothes to favourite and viewing the detailed information of clothes. Among those tests, we found it difficult to check if the image of an item in GridView showed up correctly. The withBackground() matcher in Espresso didn't perform in a way we expected after combining with the onData.check() method.

Velocity/teamwork
=================

I would say that our team realistically predicted the amount of work required for our features, however, in later iterations, the complexity of our project made those features difficult to implement. For example, our login feature had an accurate time estimate of 2 days as it was a simple feature added early on, while the time estimate for labelling clothes was underestimated by 3 days.
https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/issues/3
https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/issues/17

