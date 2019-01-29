head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingStateDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ڽ��ݽ(WEB3AdminIPOBookBuildingStateDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ڽ��ݽ�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingStateDownloadResponse extends WEB3GenResponse 
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
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * ���I�Ώې\������
     */
    public String lotTargetNumber;
    
    /**
     * �������
     */
    public String cancelNumber;
    
    /**
     * �S�\������
     */
    public String allDemandNumber;
    
    /**
     * ���ϐ\�����i
     */
    public String averageDemandPrice;
    
    /**
     * ���I�Ώې\������
     */
    public String lotTargetQuantity;
    
    /**
     * �������
     */
    public String cancelQuantity;
    
    /**
     * �S�\������
     */
    public String allDemandQuantity;
    
    /**
     * �o���]�͕ێ��ҍ��v�l��
     */
    public String paymentPowerHolderTotalNumber;
    
    /**
     * �o���]�͕ێ��ҍ��v����
     */
    public String paymentPowerHolderTotalQuantity;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j<BR>
     */
    public String displayUnitDiv;
    
    /**
     * �������敪<BR>
     * <BR>
     * �P�F�����i�i�~�j<BR>
     * �Q�F�f�B�X�J�E���g���i���j<BR>
     */
    public String temporaryConditionDiv;
    
    /**
     * @@roseuid 4112DAD602E0
     */
    public WEB3AdminIPOBookBuildingStateDownloadResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11B100272
     */
    public WEB3AdminIPOBookBuildingStateDownloadResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
