head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAttentionObjectionTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӕ����\���敪(WEB3TPAttentionObjectionTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower.define;

/**
 * (���ӕ����\���敪)
 */
public interface WEB3TPAttentionObjectionTypeDef
{

    /**
     * (���ӕ����\������)<BR>
     * <BR>
     * �ԍϑO���������F�� or�@@�~<BR>
     * �ԍό���������F�~<BR>
     */
    public final static String NO_ATTENTION = "0";

    /**
     * (���ӕ����\���L��(�ԍϑO������������)<BR>
     * <BR>
     * �ԍϑO���������F�~<BR>
     * �ԍό���������F��<BR>
     */
    public final static String ATTENTION_AFTER_REPAY = "1";

    /**
     * (���ӕ����\���L��(�ԍϑO���������L��)<BR>
     * <BR>
     * �ԍϑO���������F��<BR>
     * �ԍό���������F��<BR>
     */
    public final static String ATTENTION_BEFORE_REPAY = "2";

}
@
