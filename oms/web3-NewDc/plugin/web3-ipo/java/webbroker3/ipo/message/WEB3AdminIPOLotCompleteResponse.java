head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�����������X�|���X(WEB3AdminIPOLotCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�Ǘ���IPO���I�����������X�|���X)<BR>
 *  �Ǘ���IPO���I�����������X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminIPOLotCompleteResponse extends WEB3IPOLotCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * @@roseuid 4112DAD600B9
     */
    public WEB3AdminIPOLotCompleteResponse() 
    {
     
    }
    
    /**
     * �����󋵋敪<BR>
     * �P�F�҂�<BR>
     * �Q�F����<BR>
     * �X�F�ُ�
     */
    public String transactionStateType;
    
    /**
     * ���I�����ڍ�
     */
    public WEB3IPOLotDetailUnit[] lotDetail;
    
    /**
     * (�Ǘ���IPO���I�����������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D140A203AF
     */
    public WEB3AdminIPOLotCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
