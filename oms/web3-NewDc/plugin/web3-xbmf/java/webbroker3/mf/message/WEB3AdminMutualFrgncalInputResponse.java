head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^���̓��X�|���X(WEB3AdminMutualFrgncalInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 �����(���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[  
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �����M���C�O�s��J�����_�[�o�^���̓��X�|���X�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_mutual_frgncal_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151629L;
    
    /**
     * (���������ꗗ)<BR>
     * ���������ƂȂ铊�M�����̈ꗗ<BR>
     */
    public WEB3MutualProductCodeNameUnit[ ] mutualProductCodeNames;
    
    /**
     * (�����N���ꗗ)<BR>
     * ���������ƂȂ�N���̈ꗗ<BR>
     * ����:YYYYMM<BR>
     */
    public String[ ] searchYMList;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C0136A0365
     */
    public WEB3AdminMutualFrgncalInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualFrgncalInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
