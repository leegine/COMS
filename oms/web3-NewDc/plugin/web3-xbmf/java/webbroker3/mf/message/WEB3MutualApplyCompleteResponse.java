head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��W�����������X�|���X�N���X(WEB3MutualApplyCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * ���M��W�����������X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3MutualApplyCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_apply_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261632L;
    
    /**
     * (�X�V����)<BR>
     *  �X�V����<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     *  ���ʔԍ��i����ID�j<BR>
     */
    public String orderActionId;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualApplyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
