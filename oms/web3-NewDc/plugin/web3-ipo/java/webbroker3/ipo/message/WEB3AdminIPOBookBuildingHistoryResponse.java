head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸސ\������ڽ��ݽ(WEB3AdminIPOBookBuildingHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸސ\������ڽ��ݽ�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingHistoryResponse extends WEB3GenResponse 
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
     * �o���]��
     */
    public String paymentPower;
    
    /**
     * �\�������ꗗ
     */
    public WEB3IPODemandHistoryUnit[] demandHistoryList;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j<BR>
     */
    public String displayUnitDiv;
    
    /**
     * @@roseuid 4112DF8C015D
     */
    public WEB3AdminIPOBookBuildingHistoryResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11AE903CA
     */
    public WEB3AdminIPOBookBuildingHistoryResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
