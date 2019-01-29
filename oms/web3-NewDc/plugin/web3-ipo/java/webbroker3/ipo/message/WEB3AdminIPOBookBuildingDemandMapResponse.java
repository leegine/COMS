head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingDemandMapResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸސ\�����z�}ڽ��ݽ(WEB3AdminIPOBookBuildingDemandMapResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸސ\�����z�}ڽ��ݽ�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingDemandMapResponse extends WEB3GenResponse 
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
     * ���I�Ώې\������
     */
    public String lotTargetNumber;
    
    /**
     * ���ϐ\�����i
     */
    public String averageDemandPrice;
    
    /**
     * �������敪<BR>
     * <BR>
     * �P�F�����i�i�~�j<BR>
     * �Q�F�f�B�X�J�E���g���i���j<BR>
     */
    public String temporaryConditionDiv;
    
    /**
     * �\�����z���׈ꗗ
     */
    public WEB3IPODemandDistributionUnit distributionList;
    
    /**
     * @@roseuid 4112DF8C0276
     */
    public WEB3AdminIPOBookBuildingDemandMapResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11AFF02EF
     */
    public WEB3AdminIPOBookBuildingDemandMapResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
