head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOManagementResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����Ǘ����X�|���X(WEB3AdminIPOManagementResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���o�� �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����Ǘ����X�|���X)<BR>
 * IPO�����Ǘ����X�|���X�N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIPOManagementResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_management";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408160957L;
    
    /**
     * �V�K���ꗗ
     */
    public WEB3AdminIPOPublicOfferingProductUnit[] newListingList;
    
    /**
     * �����ꗗ
     */
    public WEB3AdminIPOPublicOfferingProductUnit[] listingList;
    
    /**
     * @@roseuid 4112E3800090
     */
    public WEB3AdminIPOManagementResponse() 
    {
     
    }
    
    /**
     * (�Ǘ���IPO�����Ǘ����X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D141510015
     */
    public WEB3AdminIPOManagementResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
