head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ԍϊ������X�|���X(WEB3OptionsCloseMarginChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
Revesion History : 2008/03/12 ����(���u) ���f�� 830
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����w���I�v�V���������ԍϊ������X�|���X)<BR>
 * �����w���I�v�V���������ԍϊ������X�|���X�N���X<BR>
 * @@author ���C�g
 * @@version 1.0 
 */
public class WEB3OptionsCloseMarginChangeCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="options_closeMarginChangeComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141516L;
    
    /**
     * (�X�V����)<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     * ���������h�c<BR>
     */
    public String orderActionId;
    
    /**
     * (�A�������ݒ�t���O)<BR>
     * �A�������ݒ�t���O<BR>
     * true�F�ݒ肠��@@�@@�@@false�F�ݒ�Ȃ�<BR>
     */
    public boolean succSettingFlag;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsCloseMarginChangeCompleteResponse()
    {
        
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */   
    protected WEB3OptionsCloseMarginChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
