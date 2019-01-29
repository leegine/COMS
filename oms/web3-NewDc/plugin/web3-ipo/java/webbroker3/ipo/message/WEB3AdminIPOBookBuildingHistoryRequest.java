head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸސ\������ظ���(WEB3AdminIPOBookBuildingHistoryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
Revesion History : 2005/01/06 ���(SRA) �c�Ή�>>>056
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸސ\������ظ��ăN���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingHistoryRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121108L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * @@roseuid 4112DF8C01DF
     */
    public WEB3AdminIPOBookBuildingHistoryRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8C01F3
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingHistoryResponse(this);
    }
}
@
