select answers.survey_id, 0 as points_version, date(str_to_date(answers.date_survey_taken, '%d/%m/%Y')) as date_survey_taken, answers.entity_id, answers.entity_type_id,

(
case
when answers.Q1='Five or more' then 0
when answers.Q1='Four' then 10
when answers.Q1='Three' then 12
when answers.Q1='Two' then 17
when answers.Q1='One' then 23
when answers.Q1='None' then 33
end +
case
when answers.Q2='No' then 0
when answers.Q2='No children ages 7 to 13' then 2
when answers.Q2='Yes' then 6
end +
case
when answers.Q3='No' then 0
when answers.Q3='Yes' then 6
when answers.Q3='No female head/spouse' then 9
end +
case
when answers.Q4='Yes' then 0
when answers.Q4='No' then 5
end +
case
when answers.Q5='Earth, sand, wood, parquet, or other' then 0
when answers.Q5='Mud bricks or cement slab' then 3
when answers.Q5='Formed cement bricks' then 9
when answers.Q5='Granite or ceramic' then 15
end +
case
when answers.Q6='No' then 0
when answers.Q6='Yes' then 9
end +
case
when answers.Q7='No' then 0
when answers.Q7='Yes' then 8
end +
case
when answers.Q8='Yes' then 0
when answers.Q8='No' then 3
end +
case
when answers.Q9='No' then 0
when answers.Q9='Yes' then 8
end +
case
when answers.Q10='No' then 0
when answers.Q10='Yes' then 3
when answers.Q10='No one works mainly in agriculture' then 4
end
) 
as ppi_score
from
(SELECT
qg.id as survey_id,
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_survey_date', qgr.response, NULL)) AS 'date_survey_taken',
qgi.entity_id as entity_id,
es.entity_type_id as entity_type_id,
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_family_members_0_to_13', qgr.response, NULL)) AS 'Q1',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_all_family_members_7_to_13_in_school', qgr.response, NULL)) AS 'Q2',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_female_head_literate', qgr.response, NULL)) AS 'Q3',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_empoyed_as_casual_labor', qgr.response, NULL)) AS 'Q4',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_house_floors', qgr.response, NULL)) AS 'Q5',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_has_refrigerator', qgr.response, NULL)) AS 'Q6',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_has_gas_electric_stove', qgr.response, NULL)) AS 'Q7',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_has_stone_mill', qgr.response, NULL)) AS 'Q8',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_has_electric_iron', qgr.response, NULL)) AS 'Q9',
GROUP_CONCAT(if(q.nickname = 'ppi_guatemala_2010_enmployed_in_agriculature_or_animals', qgr.response, NULL)) AS 'Q10'
FROM question_group_response qgr, question_group_instance qgi, question_group qg, sections_questions sq, questions q, event_sources es
WHERE qgr.question_group_instance_id = qgi.id and qgr.sections_questions_id = sq.id and sq.question_id = q.question_id and qgi.question_group_id = qg.id and qg.title="PPI Guatemala 2010" and qgi.event_source_id = es.id
GROUP BY question_group_instance_id) as answers