head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M��������t���X�|���X�N���X(WEB3MutualOrderAcceptResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �����M��������t���X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualOrderAcceptResponse extends WEB3BackResponse 
{
    /**
     *  PTYPE
     */
    public static final String PTYPE = "mutual_order_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408121015L;
    
    /**
     * (���M������t���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9A17B01B1
     */
    public WEB3MutualOrderAcceptResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualOrderAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
