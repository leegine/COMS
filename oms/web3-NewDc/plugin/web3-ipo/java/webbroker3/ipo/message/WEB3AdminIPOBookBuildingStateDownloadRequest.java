head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingStateDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ظ���(WEB3AdminIPOBookBuildingStateDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ظ��ăN���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingStateDownloadRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingStateDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121015L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112DAD60394
     */
    public WEB3AdminIPOBookBuildingStateDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD603A8
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingStateDownloadResponse(this);
    }
}
@
