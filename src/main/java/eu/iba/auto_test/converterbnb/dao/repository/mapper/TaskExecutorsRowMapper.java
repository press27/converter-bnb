package eu.iba.auto_test.converterbnb.dao.repository.mapper;

import eu.iba.auto_test.converterbnb.dao.model.Employee;
import eu.iba.auto_test.converterbnb.dao.model.TaskExecutors;
import eu.iba.auto_test.converterbnb.dao.model.TaskExecutorsStatusDirectum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class TaskExecutorsRowMapper implements RowMapper<TaskExecutors> {

    public static final Logger log = LoggerFactory.getLogger(TaskExecutorsRowMapper.class);

    @Override
    public TaskExecutors mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TaskExecutors model = new TaskExecutors();
            model.setId(rs.getLong("id"));
            model.setTaskId(rs.getLong("taskId"));
            long rkkId = rs.getLong("rkkId");
            long rkkCitizenId = rs.getLong("rkkCitizenId");
            if (rkkId != 0) {
                model.setRkkId(rkkId);
            } else {
                model.setRkkId(rkkCitizenId);
            }

            Timestamp createDate = rs.getTimestamp("createDate");
            if(createDate != null){
                model.setCreateDate(createDate.toInstant());
            }
            model.setResponsible(Objects.equals(rs.getString("responsible"), "Д") ? Boolean.TRUE : Boolean.FALSE);

            Timestamp executedDate = rs.getTimestamp("executedDate");
            if(executedDate != null){
                model.setExecutedDate(executedDate.toInstant());
            } else {
                if(createDate != null){
                    model.setExecutedDate(createDate.toInstant());
                }
            }
            model.setStampText(rs.getString("stampText"));

            Long employeeId = rs.getObject("employeeId", Long.class);
            Long additionalEmployeeId = rs.getObject("additionalEmployeeId", Long.class);
            if(employeeId != null && employeeId > 0) {
                Employee executor = new Employee();
                executor.setEmployeeId(employeeId);
                executor.setEmployeeFullName(rs.getString("employeeFullName"));
                executor.setEmployeeLoginAD(rs.getString("employeeLoginAD"));
                model.setExecutor(executor);
            } else if(additionalEmployeeId != null && additionalEmployeeId > 0){
                Employee executor = new Employee();
                executor.setEmployeeId(additionalEmployeeId);
                executor.setEmployeeFullName(rs.getString("additionalEmployeeFullName"));
                model.setExecutor(executor);
            }

            model.setTaskExecutorsStatusDirectum(convertStatus(rs.getString("taskExecutorsStatusDirectum")));
            model.setNumber(rs.getLong("number"));
            model.setParentTaskId(rs.getLong("parentTaskId"));
            return model;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private TaskExecutorsStatusDirectum convertStatus(String status) {
        try {
            if (status != null && !status.isEmpty()) {
                return switch (status) {
                    case "I" -> TaskExecutorsStatusDirectum.I;
                    case "W" -> TaskExecutorsStatusDirectum.W;
                    case "K" -> TaskExecutorsStatusDirectum.K;
                    case "N" -> TaskExecutorsStatusDirectum.N;
                    default -> TaskExecutorsStatusDirectum.C;
                };
            }
            return TaskExecutorsStatusDirectum.C;
        } catch (Exception ex){
            log.error(ex.getMessage(),ex);
            return TaskExecutorsStatusDirectum.C;
        }
    }
}
