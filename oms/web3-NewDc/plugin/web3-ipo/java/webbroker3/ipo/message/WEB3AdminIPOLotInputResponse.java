head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�������̓��X�|���X(WEB3AdminIPOLotInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�Ǘ���IPO���I�������̓��X�|���X)<BR>
 *  �Ǘ���IPO���̓��X�|���X�N���X�B<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminIPOLotInputResponse extends WEB3IPOLotCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * �����\����
     */
    public String allotAbleQuantity;
    
    /**
     * @@roseuid 4112DAD600B9
     */
    public WEB3AdminIPOLotInputResponse() 
    {
     
    }
    
    /**
     * (�Ǘ���IPO���I�������̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D140A203AF
     */
    public WEB3AdminIPOLotInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
