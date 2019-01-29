head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiConsentResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���ӏ����X�|���X(WEB3SrvRegiConsentResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
Revesion History : 2007/06/05 �����F�@@���f�� 248
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p���ӏ����X�|���X)<BR>
 * �T�[�r�X���p���ӏ����X�|���X�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiConsentResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_consent";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151442L;
    
    /**
     * (���ӏ�����)
     */
    public String consentSentence;

    /**
     * (�\�������敪)<BR>
     * 1�F�����Ώہ@@2�F�\���s��<BR>
     */
    public String applyAttributeDiv;

    /**
     * (�\����������From)<BR>
     * �\����������From(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodFrom;

    /**
     * (�\����������To)<BR>
     * �\����������To(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodTo;

    /**
     * (���������\���敪)<BR>
     * null or '0' �F�ʏ�\���@@'1'�F���������\��<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (�T�[�r�X���p���ӏ����X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F1EBAC01EB
     */
    public WEB3SrvRegiConsentResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3SrvRegiConsentResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
