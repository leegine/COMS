head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʍw���\���󋵈ꗗڽ��ݽ(WEB3AdminIPOLotResultOfferListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʍw���\���󋵈ꗗڽ��ݽ�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferListResponse extends WEB3GenResponse 
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
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j<BR>
     */
    public String displayUnitDiv;
    
    /**
     * �w���\�����ʕύX�\�t���O<BR>
     * <BR>
     * �@@true�F�@@�w���\�����ʓ��͉\�i�\���j<BR>
     * �@@false�F�@@�w���\�����ʂ𓖑I���ʂɌŒ�i��\���j<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * ���y�[�W��
     */
    public String totalPages;
    
    /**
     * �����R�[�h��
     */
    public String totalRecords;
    
    /**
     * �\���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (���I���ʈꗗ)
     */
    public WEB3IPOLotResultUnit[] lotResultList;
    
    /**
     * @@roseuid 4112DAD50017
     */
    public WEB3AdminIPOLotResultOfferListResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40EE01E30233
     */
    public WEB3AdminIPOLotResultOfferListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
