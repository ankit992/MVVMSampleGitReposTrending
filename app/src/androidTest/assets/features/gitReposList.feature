Feature: GitReposList


    @gitReposList
    Scenario: Opens The application
        Given I am a user
        Then I should see trending git repos loaded
        Then The List should have 4 items in it