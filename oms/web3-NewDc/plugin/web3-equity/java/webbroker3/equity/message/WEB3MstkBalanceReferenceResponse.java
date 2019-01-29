head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c���Ɖ�X�|���X(WEB3MstkBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i�����~�j�����c���Ɖ�X�|���X�j�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�X�|���X�N���X<BR>
 */
public class WEB3MstkBalanceReferenceResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_balance_reference";

    /**
     * (�����ꗗ)<BR>
     * <BR>
     * �����ꗗ<BR>
     */
    public WEB3MstkProductCodeNameUnit[] productCodeNames;
    
    /**
     * (�c���Ɖ��)<BR>
     *<BR>
     * ���������ɕR�t�����c�����̈ꗗ<BR>
     * �i�����~�j�����c���Ɖ�ׂ̔z��j<BR>
     */
    public WEB3MstkBalanceReferenceDetailUnit[] mstkBalanceReferenceDetail;
    
    /**
     * (���y�[�W��)<BR>
     * <BR>
     * �Y������S�y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * <BR>
     * �Y������S�f�[�^��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * @@roseuid 4206CC990396<BR>
     */
    public WEB3MstkBalanceReferenceResponse() 
    {
     
    }
    
    public WEB3MstkBalanceReferenceResponse(WEB3MstkBalanceReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
