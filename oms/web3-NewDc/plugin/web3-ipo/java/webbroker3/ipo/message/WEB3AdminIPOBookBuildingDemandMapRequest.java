head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingDemandMapRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸސ\�����z�}ظ���(WEB3AdminIPOBookBuildingDemandMapRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸސ\�����z�}ظ��ăN���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingDemandMapRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingDemandMap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121059L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112DF8C02EE
     */
    public WEB3AdminIPOBookBuildingDemandMapRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8C0302
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingDemandMapResponse(this);
    }
}
@
