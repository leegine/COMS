head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A������(WEB3AioCashinNoticeUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/
package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����A������)<BR>
 * �����A�����׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashinNoticeUnit extends Message
{
    
    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�i�����ԍ��j<BR>
     */
    public String accountCode;
    
    /**
     * (�ڋq���i�J�i�j)<BR>
     */
    public String accountName;
    
    /**
     * (�����z)<BR>
     */
    public String cashinAmt;
    
    /**
     * (�U������Z�@@�֖���)<BR>
     */
    public String finInstitutionName;
    
    /**
     * (�U����)<BR>
     */
    public Date transferDate;
    
    /**
     * (�A������)<BR>
     */
    public Date noticeDate;
    
    /**
     * (���ʃR�[�h)<BR>
     */
    public String orderRequestNumber;
    
    /**
     * (�����A������)<BR>
     * @@return webbroker3.aio.message.WEB3AioCashinNoticeUnit
     * @@roseuid 40E284450344
     */
    public WEB3AioCashinNoticeUnit() 
    {
     
    }
}
@
