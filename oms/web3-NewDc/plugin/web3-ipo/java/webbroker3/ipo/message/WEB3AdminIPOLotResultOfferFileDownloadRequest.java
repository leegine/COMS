head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʍw���\����̧���޳�۰��ظ���(WEB3AdminIPOLotResultOfferFileDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
                 : 2006/11/09 ꎉ� (���u) �d�l�ύX�E���f��160
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʍw���\����̧���޳�۰��ظ��ăN���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferFileDownloadRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121116L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * (CSV�敪) <BR>
     * CSV�敪 <BR>
     * <BR>
     * 0�F�ǉ����ږ�<BR>
     * 1�F�ǉ����ڗL�i���҃R�[�h�A���J���i�A�M�p�敪�A���I�ԍ��j<BR>
     */
    public String csvDiv = "0";
    
    /**
     * @@roseuid 4112DAD501D0
     */
    public WEB3AdminIPOLotResultOfferFileDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD501E4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferFileDownloadResponse(this);
    }
}
@
