head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginSecurityInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��K���`�F�b�N�G���[�������N���X(WEB3TPMarginSecurityInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/28 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;


/**
 * �i��K���`�F�b�N�G���[�������j
 */
public class WEB3TPMarginSecurityInfo
{

    /**
     * �i��K���Ώۖ���ID�j<BR>
     */
    public long marginSecProductId;

    /**
     * �i��K����L���j
     */
    public double marginSecRate;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3TPMarginSecurityInfo()
    {

    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("marginSecProductId", this.marginSecProductId);
        l_builder.append("marginSecRate", this.marginSecRate);

        return l_builder.toString();
    }
}
@
