head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAttentionObjection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍώ����ӕ���(WEB3TPAttentionObjection.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�ԍώ����ӕ���)
 */
public class WEB3TPAttentionObjection
{

    /**
     * (���ӕ����\���敪)<BR>
     * <BR>
     * [�ԍϑO���������F�� or �~�A�ԍό���������F�~]<BR>
     * �@@�E���ӕ����\���敪�����ӕ����\���敪Def.���ӕ����\������<BR>
     * <BR>
     * [�ԍϑO���������F���A�ԍό���������F��]<BR>
     * �@@�E���ӕ����\���敪�����ӕ����\���敪Def.���ӕ����\���L��(�ԍϑO���������L��)<BR>
     * <BR>
     * [�ԍϑO���������F�~�A�ԍό���������F��]<BR>
     * �@@�E���ӕ����\���敪�����ӕ����\���敪Def.���ӕ����\���L��(�ԍϑO������������)<BR>
     */
    public String attentionObjectionType;

    /**
     * (���������z)<BR>
     * <BR>
     * [�ԍό���������F�~]<BR>
     * �@@�E���������z��0<BR>
     * <BR>
     * [�ԍό���������F��]<BR>
     * �@@�E���������z��(���߂�)�ԍό���������z<BR>
     */
    public double demandAmount;

    /**
     * @@roseuid 41E384290161
     */
    public WEB3TPAttentionObjection()
    {

    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("attentionObjectionType", this.attentionObjectionType)
            .append("demandAmount", this.demandAmount)
            .toString();
    }
}
@
