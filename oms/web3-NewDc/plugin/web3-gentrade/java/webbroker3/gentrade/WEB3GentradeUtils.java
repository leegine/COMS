head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �r�W�l�X���ʂ̃��[�e�B���e�B(WEB3GentradeUtils)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17 ����� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BizDateTypeDef;

/**
 * �r�W�l�X���ʂ̃��[�e�B���e�B�B<BR>
 * <BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3GentradeUtils
{
    /**
     * �c�Ɠ������ߕԂ��܂��B
     *
     * @@param l_datBaseDate - ���
     * @@return �c�Ɠ�
     */
    public static Timestamp getBizDate(Date l_datBaseDate) throws WEB3BaseException
    {
        return getBizDate(l_datBaseDate, 0);
    }

    /**
     * ���������Z�E���Z�����c�Ɠ������ߕԂ��܂��B
     *
     * @@param l_datBaseDate - ���
     * @@param l_intRoll - ���Z�E���Z����
     * @@return �c�Ɠ�
     */
    public static Timestamp getBizDate(Date l_datBaseDate, int l_intRoll) throws WEB3BaseException
    {        
        Timestamp l_tsBaseDate;
        if (l_datBaseDate instanceof Timestamp)
        {
            l_tsBaseDate = (Timestamp)l_datBaseDate;
        }
        else
        {
            l_tsBaseDate = new Timestamp(l_datBaseDate.getTime());   
        }
        
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tsBaseDate);         
        
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
            
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType)) && (l_intRoll == 0))
        {
            l_intRoll = 1;
        }
        
        return l_bizDate.roll(l_intRoll);
    }
}
@
