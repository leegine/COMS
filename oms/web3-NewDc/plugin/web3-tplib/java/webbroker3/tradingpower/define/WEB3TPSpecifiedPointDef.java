head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSpecifiedPointDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �w����@@�萔��`�C���^�t�F�C�X(WEB3TPSpecifiedPointDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 nakazato(ACT) �V�K�쐬
                   2006/09/13 ���G�́@@(���u) ���f�� NO.035
Revesion History : 2007/10/16 ��іQ  (���u) ���f�� NO.198
*/
package webbroker3.tradingpower.define;

/**
 *�w����@@�萔��`�C���^�t�F�C�X
 *
 * @@version 1.0
 */
public interface WEB3TPSpecifiedPointDef
{
    /**
     * �w���(T-1):-1
     */
    public final static int T_MINUS1 = -1;

    /**
     * �w���(T-2):-2
     */
    public final static int T_MINUS2 = -2;

    /**
     * �w���(T+0):0
     */
    public final static int T_0 = 0;

    /**
     * �w���(T+1):1
     */
    public final static int T_1 = 1;

    /**
     * �w���(T+2):2
     */
    public final static int T_2 = 2;

    /**
     * �w���(T+3):3
     */
    public final static int T_3 = 3;

    /**
     * �w���(T+4):4
     */
    public final static int T_4 = 4;

    /**
     * �w���(T+5):5
     */
    public final static int T_5 = 5;
    
    /**
     * �]�͌v�Z�͈�
     */
    public final static int TP_CALC_RANGE = 5;

}
@
