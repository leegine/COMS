head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s�����������(WEB3TPShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 �����F�i���u�j�V�K�쐬 ���f��No.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�s�����������) <BR>
 * (�s����������񃆃j�b�g)<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3TPShortfallGenerationInfo extends Message
{

    /**
     * (����(T+0))<BR>
     * (����(T+0))<BR>
     */
    public Date closeDate0;
    
    /**
     * (����(T+1))<BR>
     * (����(T+1))<BR>
     */
    public Date closeDate1;
    
    /**
     * (����(T+2))<BR>
     * (����(T+2))<BR>
     */
    public Date closeDate2;
    
    /**
     * (����(T+3))<BR>
     * (����(T+3))<BR>
     */
    public Date closeDate3;
    
    /**
     * (����(T+4))<BR>
     * (����(T+4))<BR>
     */
    public Date closeDate4;
    
    /**
     * (����(T+5))<BR>
     * (����(T+5))<BR>
     */
    public Date closeDate5;
    
    /**
     * (�K�v�����z(T+0))<BR>
     * (�K�v�����z(T+0))<BR>
     */
    public String requiredPayAmt0 = "0";
    
    /**
     * (�K�v�����z(T+1))<BR>
     * (�K�v�����z(T+1))<BR>
     */
    public String requiredPayAmt1 = "0";
    
    /**
     * (�K�v�����z(T+2))<BR>
     * (�K�v�����z(T+2))<BR>
     */
    public String requiredPayAmt2 = "0";
    
    /**
     * (�K�v�����z(T+3))<BR>
     * (�K�v�����z(T+3))<BR>
     */
    public String requiredPayAmt3 = "0";
    
    /**
     * (�K�v�����z(T+4))<BR>
     * (�K�v�����z(T+4))<BR>
     */
    public String requiredPayAmt4 = "0";
    
    /**
     * (�K�v�����z(T+5))<BR>
     * (�K�v�����z(T+5))<BR>
     */
    public String requiredPayAmt5 = "0";
    
    /**
     * (���Z�z(T+0))<BR>
     * (���Z�z(T+0))<BR>
     */
    public String adjustedAmt0 = "0";
    
    /**
     * (���Z�z(T+1))<BR>
     * (���Z�z(T+1))<BR>
     */
    public String adjustedAmt1 = "0";
    
    /**
     * (���v��S����(T+0))<BR>
     * (���v��S����(T+0))<BR>
     */
    public String dayTradeRestraint0 = "0";
    
    /**
     * (���v��S����(T+1))<BR>
     * (���v��S����(T+1))<BR>
     */
    public String dayTradeRestraint1 = "0";
    
    /**
     * (�ۏ؋�����̐U�֊z(T+0))<BR>
     * (�ۏ؋�����̐U�֊z(T+0))<BR>
     */
    public String transferFromMarginDeposit0 = "0";
    
    /**
     * (�ۏ؋�����̐U�֊z(T+1))<BR>
     * (�ۏ؋�����̐U�֊z(T+1))<BR>
     */
    public String transferFromMarginDeposit1 = "0";
}
@
