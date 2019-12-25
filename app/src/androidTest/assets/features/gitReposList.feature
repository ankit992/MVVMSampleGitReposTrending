Feature: GitReposList


    @gitReposList
    Scenario: Opens The application
        Given I am a user
        Then I should see trending git repos loaded
        Then The List should have 4 items in it
        When I click on 1st Item in List
        Then I should see the RepoDetailScreen
        Then I should see the RepoName1 on screen