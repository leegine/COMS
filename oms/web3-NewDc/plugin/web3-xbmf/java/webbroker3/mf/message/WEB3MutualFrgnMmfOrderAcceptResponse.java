head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��MMF������t���X�|���X(WEB3MutualFrgnMmfOrderAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 �����F (���u) �V�K�쐬 (���f��536)
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�O��MMF������t���X�|���X)<BR>
 * �O��MMF������t���X�|���X�N���X<BR>
 * 
 * @@author �����F(���u)
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_frgn_mmf_order_accept";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200702061500L;

    /**
     * (�O��MMF������t���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 45B46D4B005F
     */
    public WEB3MutualFrgnMmfOrderAcceptResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualFrgnMmfOrderAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
