head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiApplyCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//�\�[�X �t�@@�C��: D:\\3.0.�T�[�r�X���p\\Source\\webbroker3\\srvregi\\message\\WEB3AdminSrvRegiApplyCondition.java

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�\������)<BR>
 * �T�[�r�X���p�����f�[�^�N���X<BR>
 */
public class WEB3AdminSrvRegiApplyCondition extends Message 
{

    /**
     * (�\�������L���敪)<BR>
     * 0:�����@@1:�L��<BR>
     */
    public String conditionDiv;
    
    /**
     * (�\�������R�[�h)
     */
    public String conditionCode;
    
    /**
     * (�\����������)
     */
    public String conditionName;
    
    /**
     * (�\�������l)
     */
    public String conditionValue;
    
    /**
     * (�\�������l�L��)<BR>
     * 0�F���@@1�F�L<BR>
     */
    public String conditionExist;
    
    /**
     * (�T�[�r�X���p�\������)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40FFA3C40055
     */
    public WEB3AdminSrvRegiApplyCondition() 
    {
     
    }
}
@
