
select  ci.COMMAND, array_agg(ca.ARG)
from    COMMAND_INVOCATION ci
            inner join
        COMMAND_ARGUMENT ca on ca.COMMAND_INVOCATION_ID = ci.id
group by ci.id;
;
