head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����M�����������o�^�m�F���X�|���X(WEB3AdminMutualConditionsConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M�����������o�^�m�F���X�|���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131055L;
    
    /**
     * (���M���������o�^�m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40DF81C00006
     */
    public WEB3AdminMutualConditionsConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualConditionsConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
