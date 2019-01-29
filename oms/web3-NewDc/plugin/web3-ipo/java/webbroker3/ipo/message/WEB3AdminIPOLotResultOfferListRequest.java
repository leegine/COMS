head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʍw���\���󋵈ꗗظ���(WEB3AdminIPOLotResultOfferListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʍw���\���󋵈ꗗظ��ăN���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121118L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * �y�[�W���\���s��
     */
    public String pageSize;
    
    /**
     * (�\�[�g�L�[)
     */
    public WEB3IPOSortKey[] sortKeys;
    
    /**
     * @@roseuid 4112DAD500CB
     */
    public WEB3AdminIPOLotResultOfferListRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD500DF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferListResponse(this);
    }
}
@
