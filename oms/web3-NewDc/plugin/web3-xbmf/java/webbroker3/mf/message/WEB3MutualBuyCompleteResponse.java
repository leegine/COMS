head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�����������X�|���X�N���X(WEB3MutualBuyCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ���E (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M�����t�����������X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_complete";
    
    /**
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * ���ʔԍ�
     */
    public String orderActionId;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * (���M���t�����������X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A883C701EC
     */
    public WEB3MutualBuyCompleteResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualBuyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
