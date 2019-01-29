head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointCategoryMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.point.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link PointCategoryMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link PointCategoryMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see PointCategoryMasterPK 
 * @@see PointCategoryMasterRow 
 */
public class PointCategoryMasterDao extends DataAccessObject {


  /** 
   * ����{@@link PointCategoryMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private PointCategoryMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link PointCategoryMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link PointCategoryMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link PointCategoryMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link PointCategoryMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointCategoryMasterRow )
                return new PointCategoryMasterDao( (PointCategoryMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointCategoryMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointCategoryMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link PointCategoryMasterRow}�I�u�W�F�N�g 
    */
    protected PointCategoryMasterDao( PointCategoryMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link PointCategoryMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public PointCategoryMasterRow getPointCategoryMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link PointCategoryMasterRow}�I�u�W�F�N�g����{@@link PointCategoryMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link PointCategoryMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link PointCategoryMasterDao}�擾�̂��߂Ɏw���{@@link PointCategoryMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link PointCategoryMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static PointCategoryMasterDao forRow( PointCategoryMasterRow row ) throws java.lang.IllegalArgumentException {
        return (PointCategoryMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointCategoryMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link PointCategoryMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link PointCategoryMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link PointCategoryMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointCategoryMasterRow.TYPE );
    }


  /** 
   * {@@link PointCategoryMasterRow}����ӂɓ��肷��{@@link PointCategoryMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link PointCategoryMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link PointCategoryMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link PointCategoryMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static PointCategoryMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link PointCategoryMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_categoryNo �����Ώۂł���p_categoryNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PointCategoryMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PointCategoryMasterRow findRowByPk( String p_institutionCode, int p_categoryNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( p_institutionCode, p_categoryNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���PointCategoryMasterPK�I�u�W�F�N�g����{@@link PointCategoryMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����PointCategoryMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PointCategoryMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PointCategoryMasterRow findRowByPk( PointCategoryMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointCategoryMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,int)}�����{@@link #forRow(PointCategoryMasterRow)}���g�p���Ă��������B 
   */
    public static PointCategoryMasterDao findDaoByPk( String p_institutionCode, int p_categoryNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( p_institutionCode, p_categoryNo );
        PointCategoryMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(PointCategoryMasterPK)}�����{@@link #forRow(PointCategoryMasterRow)}���g�p���Ă��������B 
   */
    public static PointCategoryMasterDao findDaoByPk( PointCategoryMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointCategoryMasterRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link PointCategoryMasterDao}�Ɋ֘A����{@@link PointCategoryMasterRow}�̊O���L�[������{@@link PointPremiumMasterRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link PointCategoryMasterDao}�Ɋ֘A����{@@link PointCategoryMasterRow}�̊O���L�[������{@@link PointPremiumMasterRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchPointPremiumMasterRowsByInstitutionCodeCategoryNo() throws DataNetworkException, DataQueryException  {
        return PointPremiumMasterDao.findRowsByInstitutionCodeCategoryNo( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchPointPremiumMasterRowsByInstitutionCodeCategoryNo()}�����{@@link #forRow(PointCategoryMasterRow)}���g�p���Ă��������B 
   */
    public List fetchPointPremiumMasterDaosByInstitutionCodeCategoryNo() throws DataNetworkException, DataQueryException  {
        return PointPremiumMasterDao.findDaosByInstitutionCodeCategoryNo( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchPointPremiumMasterRowsByInstitutionCodeCategoryNo()}�����{@@link #forRow(PointCategoryMasterRow)}���g�p���Ă��������B 
   */
    public List fetchPointPremiumMasterDaosInstitutionCodeCategoryNo() throws DataNetworkException, DataQueryException  {
        return fetchPointPremiumMasterDaosByInstitutionCodeCategoryNo();
    }


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_categoryNo, and �ɂĎw��̒l�����ӂ�{@@link PointCategoryMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_categoryNo �����Ώۂł���p_categoryNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_categoryNo, and �̒l�ƈ�v����{@@link PointCategoryMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static PointCategoryMasterRow findRowByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointCategoryMasterRow.TYPE,
            "institution_code=? and category_no=?",
            null,
            new Object[] { p_institutionCode, new Integer(p_categoryNo) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointCategoryMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointCategoryMasterDao.findRowsByInstitutionCodeCategoryNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeCategoryNo(String, int)}�����{@@link #forRow(PointCategoryMasterRow)}���g�p���Ă��������B 
   */
    public static PointCategoryMasterDao findDaoByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCategoryNo( p_institutionCode, p_categoryNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
