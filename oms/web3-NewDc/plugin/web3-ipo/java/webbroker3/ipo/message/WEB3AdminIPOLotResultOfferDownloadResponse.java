head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʍw���\�����޳�۰��ڽ��ݽ(WEB3AdminIPOLotResultOfferDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʍw���\�����޳�۰��ڽ��ݽ�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121114L;
    
    /**
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * �A�b�v���[�h����
     */
    public WEB3IPOUploadHistoryUnit uploadHistory;
    
    /**
     * ���Ў戵����
     */
    public String handlingQuantity;
    
    /**
     * �����m�萔��
     */
    public String allotQuantity;
    
    /**
     * �J��\����
     */
    public String advanceQuantity;
    
    /**
     * �J��҂�����
     */
    public String advanceWaitQuantity;
    
    /**
     * ���I�ҍw���\���T��
     */
    public WEB3IPOOfferConditionUnit prizedOfferCondition;
    
    /**
     * �⌇�ҍw���\���T��
     */
    public WEB3IPOOfferConditionUnit waitingOfferCondition;
    
    /**
     * ���I�ҍw���\���T��
     */
    public WEB3IPOOfferConditionUnit rejectedOfferCondition;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j<BR>
     */
    public String displayUnitDiv;
    
    /**
     * @@roseuid 4112DAD50298
     */
    public WEB3AdminIPOLotResultOfferDownloadResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E12495005F
     */
    public WEB3AdminIPOLotResultOfferDownloadResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
