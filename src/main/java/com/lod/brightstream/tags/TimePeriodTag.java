package com.lod.brightstream.tags;

import com.lod.brightstream.utils.TimeCalc;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



public class TimePeriodTag extends TagSupport  {
    
    private Timestamp initialDate;
    private Timestamp finalDate;
    
    public void setInitialDate(Timestamp initialDate) {
        this.initialDate = initialDate;
    }
    
    public void setFinalDate(Timestamp finalDate) {
        this.finalDate = finalDate;
    }
    
    @Override
    public int doStartTag() throws JspException {
        
        String meaningfulDiff = TimeCalc.getMeaningfulTimePeriod(initialDate, finalDate);
        
        try {
            JspWriter out = pageContext.getOut();
            out.print(meaningfulDiff);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return SKIP_BODY;
    }
    
}
