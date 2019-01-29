head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	DisplayContentsDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.pvinfo.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link DisplayContentsDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DisplayContentsRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DisplayContentsPK 
 * @@see DisplayContentsRow 
 */
public class DisplayContentsDao extends DataAccessObject {


  /** 
   * ����{@@link DisplayContentsDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DisplayContentsRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DisplayContentsRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DisplayContentsDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DisplayContentsDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DisplayContentsRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DisplayContentsRow )
                return new DisplayContentsDao( (DisplayContentsRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DisplayContentsRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DisplayContentsRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DisplayContentsRow}�I�u�W�F�N�g 
    */
    protected DisplayContentsDao( DisplayContentsRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DisplayContentsRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DisplayContentsRow getDisplayContentsRow() {
        return row;
    }


  /** 
   * �w���{@@link DisplayContentsRow}�I�u�W�F�N�g����{@@link DisplayContentsDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DisplayContentsRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DisplayContentsDao}�擾�̂��߂Ɏw���{@@link DisplayContentsRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DisplayContentsDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DisplayContentsDao forRow( DisplayContentsRow row ) throws java.lang.IllegalArgumentException {
        return (DisplayContentsDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DisplayContentsRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DisplayContentsRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DisplayContentsPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DisplayContentsParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DisplayContentsRow.TYPE );
    }


  /** 
   * {@@link DisplayContentsRow}����ӂɓ��肷��{@@link DisplayContentsPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DisplayContentsRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DisplayContentsParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DisplayContentsPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DisplayContentsPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new DisplayContentsPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DisplayContentsRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_displayContentsId �����Ώۂł���p_displayContentsId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DisplayContentsRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DisplayContentsRow findRowByPk( long p_displayContentsId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayContentsPK pk = new DisplayContentsPK( p_displayContentsId );
        return findRowByPk( pk );
    }


  /** 
   * �w���DisplayContentsPK�I�u�W�F�N�g����{@@link DisplayContentsRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DisplayContentsPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DisplayContentsRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DisplayContentsRow findRowByPk( DisplayContentsPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DisplayContentsRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(DisplayContentsRow)}���g�p���Ă��������B 
   */
    public static DisplayContentsDao findDaoByPk( long p_displayContentsId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayContentsPK pk = new DisplayContentsPK( p_displayContentsId );
        DisplayContentsRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DisplayContentsPK)}�����{@@link #forRow(DisplayContentsRow)}���g�p���Ă��������B 
   */
    public static DisplayContentsDao findDaoByPk( DisplayContentsPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayContentsRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_displayContentsId, and �ɂĎw��̒l�����ӂ�{@@link DisplayContentsRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_displayContentsId �����Ώۂł���p_displayContentsId�t�B�[���h�̒l
   * 
   * @@return �����w���p_displayContentsId, and �̒l�ƈ�v����{@@link DisplayContentsRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DisplayContentsRow findRowByDisplayContentsId( long p_displayContentsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DisplayContentsRow.TYPE,
            "display_contents_id=?",
            null,
            new Object[] { new Long(p_displayContentsId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DisplayContentsRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DisplayContentsDao.findRowsByDisplayContentsId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByDisplayContentsId(long)}�����{@@link #forRow(DisplayContentsRow)}���g�p���Ă��������B 
   */
    public static DisplayContentsDao findDaoByDisplayContentsId( long p_displayContentsId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByDisplayContentsId( p_displayContentsId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_conditionNo, and �ɂĎw��̒l�Ɉ�v����{@@link DisplayContentsRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_conditionNo �����Ώۂł���p_conditionNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_conditionNo, and �̒l�ƈ�v����{@@link DisplayContentsRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DisplayContentsRow.TYPE,
            "institution_code=? and condition_no=?",
            null,
            new Object[] { p_institutionCode, p_conditionNo } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeConditionNo(String, String)}�����{@@link #forRow(DisplayContentsRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeConditionNo( p_institutionCode, p_conditionNo ) );
    }

}
@
