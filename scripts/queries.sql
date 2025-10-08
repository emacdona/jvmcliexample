
select  ci.COMMAND, array_agg(ca.ARG)
from    COMMAND_INVOCATION ci
            left join
        COMMAND_ARGUMENT ca on ca.COMMAND_INVOCATION_ID = ci.id
group by ci.id, ci.COMMAND;
;

select count(*) from COMMAND_INVOCATION;