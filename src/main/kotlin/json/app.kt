package json

fun main() {
    val jsonResponse = """
    {
    "RequestId": "e711016a-895d-4b1f-8fe8-ced938a030cc",
    "Status": "success",
    "Debug": {
        "StatusCode": 200,
        "StatusText": "OK"
    },
    "Data": [
        {
            "GoalDefinitionId": "ed77ff87-c71e-42ea-ac81-c33a8cb61a37",
            "Description": "Babysit sibling",
            "IconType": "babysut_sibling",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "d56ab847-1ae1-4454-9023-5335c84b2d52",
            "Description": "Brush my teeth",
            "IconType": "brush_my_teeth",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "2e6c88f6-2664-488d-9f90-e9f48ec73d2e",
            "Description": "Clean my bedroom",
            "IconType": "clean_my_bedroom",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "0c7e5ba2-1b8d-4388-9a57-fb1718f0fd94",
            "Description": "Clean the bathroom",
            "IconType": "clean_the_bathroom",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "032dca3e-f898-46b5-9428-c5ca331280a3",
            "Description": "Do the laundry",
            "IconType": "do_the_laundry",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "2c81e6f8-9352-49e3-a1e9-b55831ffb96d",
            "Description": "Do the vacuuming",
            "IconType": "vacuuming",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "51837f3d-4d0c-4ce9-9835-5d97a0611e8c",
            "Description": "Eat fruit & vegetables",
            "IconType": "eat_fruits",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "8aaa32a0-bddb-4af0-8d8e-97b72893a4e8",
            "Description": "Get good grades",
            "IconType": "get_good_rates",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "b057fd8b-5971-4c48-8b63-a133dc0efb23",
            "Description": "Get ready for school",
            "IconType": "get_ready_to_school",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "a71cad9e-ab3c-4cfa-b91e-bb8594174c29",
            "Description": "Get school prize",
            "IconType": "get_school_prize",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "f89d26fc-f820-4da9-a5fa-b4acdb1d315e",
            "Description": "Good attitude",
            "IconType": "good_attitude",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "eb8cde6b-681f-4795-b1ad-f0aa2e12d554",
            "Description": "Load / empty the dishwasher",
            "IconType": "load_diswasher",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "28119842-2bf7-460b-a6c1-4a121137565c",
            "Description": "Load washing machine",
            "IconType": "load_washing_machine",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "a599f16a-2f99-4288-b70d-ad726c1c541c",
            "Description": "Make my bed",
            "IconType": "make_my_bed",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "c23db27b-4dfc-48b5-9f09-602fe2c995e8",
            "Description": "Mop",
            "IconType": "mop",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "58314002-8e3f-4449-92be-29f5f89bd668",
            "Description": "Mow the lawn",
            "IconType": "mow_the_lawn",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "b1f773bb-17b5-4435-bc43-5d13c73802c0",
            "Description": "Practice instrument",
            "IconType": "practice_instrument",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "339e1682-3049-4d2d-b2b6-52eccfd8cf2e",
            "Description": "Practice sport",
            "IconType": "practice_sport",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "7bb0f367-d323-44ad-8b23-fa3ae89793e5",
            "Description": "Put groceries away",
            "IconType": "put_groceries_away",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "648a06f7-e47e-4f0f-97d7-c6b16a85a65f",
            "Description": "Read every day",
            "IconType": "read_every_day",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "1899e035-90d5-4f68-afb9-9d742f8c61e5",
            "Description": "Recycle trash",
            "IconType": "recycle_trash",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "613d972c-0368-43b9-9013-bce58b25f0a4",
            "Description": "Set/Clear the table",
            "IconType": "set_table",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "eef32080-61d8-4b8e-a7f7-dbc6ceb6e358",
            "Description": "Shovel Snow",
            "IconType": "shovel_snow",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "2c746944-0693-4fdc-a2b0-71fef9f8f1d3",
            "Description": "Sweep the yard",
            "IconType": "sweep_the_yard",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "257bca75-71e3-4c3a-9aed-1888609eafcf",
            "Description": "Take care of the pet",
            "IconType": "take_care_of_pet",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "614b46e1-6387-4440-b15b-dbe33ed8253a",
            "Description": "Take out the trash",
            "IconType": "take_out_trash",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "d0b234fd-e55a-4d63-8f78-30442fc53eca",
            "Description": "Tidy my clothes",
            "IconType": "tidy_my_clothes",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "d8d2f0c4-8aca-4a3f-8a18-a99d078d789b",
            "Description": "Wash the car",
            "IconType": "wash_the_car",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "da3a7cfa-7f14-4c41-919a-bf28aef50df8",
            "Description": "Water plants",
            "IconType": "water_plants",
            "RewardValue": 10,
            "IsCustom": false
        },
        {
            "GoalDefinitionId": "8ff5d92c-8db1-4f1a-9316-643d88210c5a",
            "Description": "Get a Job",
            "IconType": "work",
            "RewardValue": 10,
            "IsCustom": true
        },
        {
            "GoalDefinitionId": "a91270e6-01f7-46d1-9fb8-fbfaedbb45d7",
            "Description": "Start Coding",
            "IconType": "work",
            "RewardValue": 10,
            "IsCustom": true
        }
    ]
}
"""
    val jsonLexAnalyzer = JsonLexAnalyzerImpl()
    val result = jsonLexAnalyzer.analyze(jsonResponse)
    val parser = JsonParser()
    val jsonData = parser.parse(result)
}