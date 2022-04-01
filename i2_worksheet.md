# Iteration 2 Worksheet
## Paying off Technical Debt
**1. Passing data between activities.**
In iteration 1, this was done using Parcelables and pieces of data were passed as needed. For example, the closet page gave the name, image, and tags separately to the detail page to be displayed. In this iteration, we found we needed a wider scope of the clothing item in order to edit it and keep the changes. The classes now implement Serializable and the detail page gets passed the database, account, closet and the item itself. This is deliberate and reckless debt, as we were aware that only passing individual elements was probably not ideal for editing later on, but we decided to focus on getting the information on the screen for the time being. One the database was further developed, we had a better idea of what data the page needed and could make the changes needed to pay off the debt. 
[Commit that initially makes the change](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/commit/81656976a59ba30698c156f1554323f659983a5f#0e5f47b089ef8dd387bc7b814f5978b4ac126e59_74_75) and [commit that improves it](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/commit/d8f92780e8a0c2008b44d3dc3aa3d31b447c4686#0e5f47b089ef8dd387bc7b814f5978b4ac126e59_82_81)
**2. Favourite from a tag to a boolean.**
In iteration 1, we implemented the “favourite” attribute of a clothing item as a Tag. As we worked on the ability to edit tags and favourites, we found that it was much simpler to define them separately since they were used in different ways (e.g. a button to toggle favourite shouldn’t affect other tags, and favourite shouldn’t show up in the list of other tags). This was inadvertent and reckless debt, as we figured tacking on favourite as a tag would be simple since all clothing items have tags anyways, but only found that it was the more complicated solution when we arrived at that part of the project.
[Commit](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/commit/03361c9b668ce050c36e2d77a45c2fe84a9d09e4#a301a6d42bf0f0b2903021a48d39c34f63f95ab5_68_80)

## SOLID
Looking through the project file, we found out there is a single responsibility principle violation in PU.java. 
In the PU class file there is a method called getUserID() Inside this method, it uses user.getUserId() which is a user's method. This is a single responsibility principle violation.
[Issue](https://code.cs.umanitoba.ca/winter-2022-a01/group-8/fair-price/-/issues/21)

## Retrospective
After completing the retrospective for iteration 1, our team decided to review the time estimates for our planned features. We tried to learn from our work on iteration 1 so we could create more accurate time estimates for iteration 2. We noticed that working with the UI in android studio was challenging for us, so we increased the time estimates for features that involve UI work. For example, the feature for adding or removing clothing involves both creating methods for the functionality, as well as creating UI components that have to be connected. So, to account for this, we gave that feature a longer time estimate of 4 days.
[Issue](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/issues/14)

## Design Patterns
We use the Adapter design pattern in the GridAdapter classes. [Commit](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/commit/f7b7bc07674fcb3573da848e9eece1c2f52b9228) and the [File](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/blob/main/app/src/main/java/com/example/virtualcloset/logic/GridAdapter.java)
## Iteration 1 Feedback Fixes
A grader mentioned that we should change how the Tag types are implemented. (An example for context: the Tag name is “red” whereas the type is “colour”.) We had envisioned adding Tag types as the user typing in a String and being responsible for keeping them consistent across different items, but the grader suggested using enum instead to limit their options. As we worked on the activities that would allow the user to add new items and tags for this iteration, we ultimately decided to just remove the Tag types. We figured that in the next iteration when developing how to organise the closet, we would focus on filtering by Tag name. Having Tag types as well seemed too complicated or ambitious, although possibly something we could go back to if we had the time.
[Commit](https://code.cs.umanitoba.ca/winter-2022-a01/group-9/virtualcloset-A01/-/commit/cdce1912f3a5b554d1217896fa95b6c971c0bba5)
